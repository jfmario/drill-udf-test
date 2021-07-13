# docker script for testing jars

# create the volume directory and run this once to let it populate
# before adding jars to it

docker rm -f drill
docker volume rm drill_3p_vol

docker volume create --driver local \
    --opt type=none \
    --opt device=/home/ec2-user/3rdparty \
    --opt o=bind \
    drill_3p_vol

docker run -i \
        -p 8080:8047 \
        --volume drill_3p_vol:/opt/drill/jars/3rdparty \
        --name drill \
        -t apache/drill:1.18.0 /bin/bash
        
