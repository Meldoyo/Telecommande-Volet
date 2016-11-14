#Telecommande pour volet

##Installation sur raspberry

Installer Flask

```pip install flask```

Installer le mod pour deployer sur apache

```apt-get install libapache2-mod-wsgi```

Le code d'envoi est a éxécuter par le backend
Le mettre en SUID, le placer dans /usr/local/bin/

```chmod 4755 send```

##Utilisation de send
./send [numéro de pin] [numéro de télécommande] [0|1|2] [on|off]
