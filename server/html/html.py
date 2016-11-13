from flask import Flask, jsonify
from gpiozero import LED
from time import sleep

app = Flask(__name__)

led = LED(11)

dict = {'result': 'ok'}
@app.route("/on")
def on():
	led.on()
	return jsonify(**dict)
@app.route("/off")
def off():
	led.off()
	return jsonify(**dict)
if __name__ == "__main__":
    app.run()
