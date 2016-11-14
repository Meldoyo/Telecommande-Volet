from flask import Flask, jsonify, request
from gpiozero import LED
from time import sleep
from subprocess import call

app = Flask(__name__)

led = LED(11)

dict = {'result': 'ok'}
dictChambreNumero = {'chambreParents': '45476341', 'salon4': '86545832', 'salon3': '78254586', 'bureau': '15789437', 'chambreGE': '549873', 'relais': '12325261'}
	
@app.route("/on")
def on():
	chambre = request.args.get("salle")
	numero = dictChambreNumero[chambre]
	call(["/usr/local/bin/send", "8", numero, "1", "on"])
	return jsonify(**dict)
@app.route("/off")
def off():
	chambre = request.args.get("salle")
	numero = dictChambreNumero[chambre]
	call(["/usr/local/bin/send", "8", numero, "1", "off"])
	return jsonify(**dict)
if __name__ == "__main__":
    app.run()
