#Telecommande pour volet

##Installation sur raspberry

Installer Flask

```pip install flask```

Installer le mod pour deployer sur apache

```apt-get install libapache2-mod-wsgi```

Changer les droits 

Ajouter pi au groupe gpio

```sudo chown root.gpio /dev/gpiomem

sudo chmod g+rw /dev/gpiomem```
