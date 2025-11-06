import time
from gpiozero import LED
from w1thermsensor import W1ThermSensor

def led_on():
     leds = [LED(20),LED(21),LED(22),LED(23),LED(24),LED(25),LED(26),LED(27),LED(28)]
     for led in leds:
        led.on()
        

def led_off():
     leds = [LED(20),LED(21),LED(22),LED(23),LED(24),LED(25),LED(26),LED(27),LED(28)]
     for led in leds:
        led.off()

sensor = W1ThermSensor()

while True:
     temp = sensor.getTemperature()
     print("Temperature :"+temp)
     if temp > 29:
         print("temperature exceeded")
         led_on()
     else:
         led_off()
     time.sleep(1)