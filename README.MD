In the project I use Java language with SpringBoot framework. For the page not to be reloaded, I use AJAX to send and receive data.
Working process:

1) User enters data
2) Check data
3) Data processing
4) Return required data

Data Structure:  

+) StringLedis: I use HashMap which stores 2 values of type String

+) SetLedis: I use HashMap to store String values and 1 Set of String type.

Utils:

+) Expire: I try to check on both set and string.

+) Snapshot: I try to reserve sets and strings into temporary variables.

![alt](https://firebasestorage.googleapis.com/v0/b/ledis-3458f.appspot.com/o/Untitled%20Diagram.drawio.png?alt=media&token=db8d6f0b-aab3-4740-b118-459e12362838)
