from flask import Flask, jsonify, request, Response
from time import sleep
from subprocess import call
from json import dumps

app = Flask(__name__)

dict = {'result': 'ok'}
dictChambreNumero = {'chambreParents': '45476341', 'salon4': '557862', 'salon3': '78254586', 'bureau': '15789437', 'chambreGE': '549873', 'relais': '12325261'}
dictChambreNom = {'chambreParents': 'Chambre Parents', 'salon4': 'Salon Grand', 'salon3': 'Salon Petit', 'bureau': 'Bureau', 'chambreGE': 'Chambre GE', 'relais': 'Relais'}
array = []
for key, value in dictChambreNom.iteritems():
	array.append({'name': key, 'common_name': value})

	
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
	print 'hello'
	call(["/usr/local/bin/send", "8", numero, "1", "off"])
	return jsonify(**dict)
@app.route("/get")
def get():
	return Response(dumps(array), mimetype='application/json')
if __name__ == "__main__":
    app.run()
