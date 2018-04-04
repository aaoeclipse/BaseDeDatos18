import psycopg2
conn = psycopg2.connect("host=localhost dbname=CRM user=postgres")
cursor = conn.cursor()
nombres = ["santiago", "juanito", "luisa", "rudy", "uim", "diego"]
apellido = ["paiz", "lopez", "arboleda", "garrido", "solorzano", "sosa"]
salario = [5000.00,1000.00,10000.01,500.00,1000.00,6000.00]
direccion = ["zona 10", "zona 16", "zona 120", "zona 1", "zona 5", "zona 9"]
horario = ["dia","temprano","noche","dos horas","no se", "ruuuudt"]
departamento = ["guatemala", "peten", "izabal","jutiapa","jalapa","san marcos"]
idp = ["1","2","1","2","2","2"]
idt = ["2","1","3","1","2","3"]
idpr = ["1","1","1","1","1","1"]
countE = 1
for i in range(len(nombres)):

    query =  'INSERT INTO "Empleado" (id, nombre, apellido,salario,direccion,horario,departamento,id_puesto,id_tecnologia, "id_Proyecto") VALUES ('+str(countE)+',"'+nombres[i]+'","'+apellido[i]+'",'+str(salario[i])+',"'+horario[i]+'","'+departamento[i]+'",'+str(idp[i])+','+str(idt[i])+','+str(idpr[i])+');'
    cursor.execute(query)
    countE+=1
conn.commit()