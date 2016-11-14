from flask import Flask, jsonify, request
from time import sleep
from subprocess import call
from json import dumps

app = Flask(__name__)

dict = {'result': 'ok'}
dictChambreNumero = {'chambreParents': '45476341', 'salon4': '86545832', 'salon3': '78254586', 'bureau': '15789437', 'chambreGE': '549873', 'relais': '12325261'}
array = []
for key, value in dictChambreNumero.iteritems():
	array.append({'name': key, 'id': value})

	
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
@app.route("/get")
def get():
	return dumps(array)
if __name__ == "__main__":
    app.run()
