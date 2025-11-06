import time
import RPi.GPIO as g

led_pins = [20, 21, 22, 23, 24, 25, 26, 27]

g.setmode(g.BCM)

for pin in led_pins:
    g.setup(pin, g.OUT)
    g.output(pin, g.HIGH)

try:
    while True:
        for pin in led_pins:
            g.output(pin, g.HIGH)
            time.sleep(0.5)
            g.output(pin, g.LOW)
            time.sleep(0.5)
except KeyboardInterrupt:
    print("Program closed")
finally:
    g.cleanup()