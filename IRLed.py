import time
import RPi.GPIO as g

# Pin configuration
IR_SENSOR = 5
LED = 8

# Setup
g.setmode(g.BCM)
g.setup(IR_SENSOR, g.IN, pull_up_down=g.PUD_UP)
g.setup(LED, g.OUT)

count = 0
print("\nCounting using IR Sensor\n--------------------------")

try:
    while True:
        if g.input(IR_SENSOR) == g.LOW:
            count += 1
            print(f"Person count = {count}")
            g.output(LED, g.LOW)
            time.sleep(1)
            g.output(LED, g.HIGH)
        time.sleep(0.3)

except KeyboardInterrupt:
    print("\nStopped by user.")

finally:
    g.cleanup()