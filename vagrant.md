# Prepare Vagrant for development

## Plugins

### Get ip of the guest

    vagrant plugin install vagrant-guestip
    
Usage    
    
    vagrant guestip



Digitalocean installation:

    vagrant plugin install vagrant-digitalocean
    vagrant box add digital_ocean https://github.com/smdahlen/vagrant-digitalocean/raw/master/box/digital_ocean.box
