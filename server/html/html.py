from flask import Flask, jsonify
from subprocess import call

app = Flask(__name__)

dict = {'result': 'ok'}
@app.route("/on")
def on():
	call(["/usr/local/bin/send", "8", "12325261", "1", "on"])
	return jsonify(**dict)
@app.route("/off")
def off():
	call(["/usr/local/bin/send", "8", "12325261", "1", "off"])
	return jsonify(**dict)
if __name__ == "__main__":
    app.run()
