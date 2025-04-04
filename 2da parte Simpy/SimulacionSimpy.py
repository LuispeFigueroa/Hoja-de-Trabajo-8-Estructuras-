import simpy
import random
import numpy as np
import matplotlib.pyplot as plt

# EL proceso fue inspirado en la informacion encontrada en https://www.advocatehealth.com/health-services/emergency-services/what-happens-in-the-emergency-department

random.seed(10) # valor indicado en la guia

Tiempo_Paciente = [] # lista para guardar tiempos y poder hacer estadisticas

#En esta simluacion, se espera que los casos simples puedan ser resueltos por enfermeras de prioridad  5-3, de prioridad 1-2se necesitaran doctores.

class Hospital(object):
    def __init__(self, env, num_doctores, num_enfermeras, num_rayosX):
        self.env = env
        self.doctores = simpy.PriorityResource(env, num_doctores)
        self.enfermeras = simpy.PriorityResource(env, num_enfermeras)
        self.rayosX = simpy.PriorityResource(env, num_rayosX)
    
    def triage(self, paciente): # triage es el periodo donde se le asigna prioridad al paciente.
        yield self.env.timeout(5) # se tardara 5 unidades de tiempo en asignar prioridad

    def atencion_Doctor(self, paciente):
        yield self.env.timeout(15) # casos serios que podrian necesitar rayos X, por lo que se tardan mas tiempo

    def atencion_Enfermera(self, paciente):
        yield self.env.timeout(10) #Las enfermeras atienden casos menos severos, por lo que se tardan menos tiempo
    
    def tomar_RayosX(self, paciente):
        yield self.env.timeout(10) # los pacientes que necesiten rayos X se tardan un poco mas
def paciente (env, nombre, hospital):
    llegada = env.now
    print(f"El paciente {nombre}, llego a las {llegada:.2f} al hospital")

    #Asignar prioridad
    with hospital.enfermeras.request(priority= 5) as req:
        yield req
        yield env.process(hospital.triage(nombre))
        prioridad = random.randint(1,5) # se le asgina aleatoriamente al paciente su prioridad
        print(f"El paciente {nombre}, tiene prioridad {prioridad}")

        #Atenciones segun prioridad

        #Usar true or false para definir si se necesitan rayos X
        necesita_rayosX = random.choice([True, False])
        #Prioridad severa 1-2, con doctor
        if prioridad >=2:
            with hospital.doctores.request() as req:
                yield req
                yield env.process(hospital.atencion_Doctor(nombre))
                print(f"El paciente {nombre}, fue atendido por un doctor")
            

        else: 
            #Prioridad baja 3-5, con enfermera
            with hospital.enfermeras.request() as req:
                yield req
                yield env.process(hospital.atencion_Enfermera(nombre))
                print(f"El paciente {nombre}, fue atendido por una enfermera")
                #Rayos X = True
        print(f'El paciente{nombre} necesita revisarse en rayos X')
        with hospital.rayosX.request(priority = prioridad) as req:
            yield req
            yield env.process(hospital.tomar_RayosX(nombre))
        print(f' El paciente: {nombre} recibio rayosX y salio a las {env.now:.2f}')

        Tiempo_Salida = env.now
        print( f'EL paciente {nombre} fue antendido. Tiempo total de atencion: {Tiempo_Salida - llegada:.2f}')
        Tiempo_Paciente.append(Tiempo_Salida - llegada)



def genPacientes(env, hospital, intervalo):
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0/intervalo))
        i +=1
        env.process(paciente(env, f'paciente{i}', hospital))
                    
#definir parametros Simulacion
env = simpy.Environment()
hospital = Hospital(env, num_doctores=7, num_enfermeras=4, num_rayosX=2)
env.process(genPacientes(env, hospital, intervalo=7))

env.run(until = 200)


# Gráfica
plt.hist(Tiempo_Paciente, bins=20, color='red', edgecolor='black')
plt.title("tiempo total en el hospital por paciente")
plt.xlabel("Tiempo en el Hospital")
plt.ylabel("Número de pacientes")
plt.grid(True)
plt.show()

        


        






