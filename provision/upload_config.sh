#!/bin/bash
set -eo pipefail

docker_file="Dockerrun.aws.json"
config_file=".config"
ebextensions_dir=".ebextensions"

cat <<EOF >${docker_file}
{
  "AWSEBDockerrunVersion": "1",
  "Image": {
    "Name": "${AWS_ECR_URI}/${APPLICATION_NAME}:${TRAVIS_BUILD_NUMBER}",
    "Update": "true"
  },
  "Ports": [
    {
      "ContainerPort": "80"
    }
  ],
  "environment" : [
      { "name" : "CLIENT_ID", "value" : "${CLIENT_ID}" },
      { "name" : "CLIENT_SECRET", "value": "${CLIENT_SECRET}" },
      { "name" : "TOKEN_INFO_URI", "value": "${TOKEN_INFO_URI}" }
  ],
  "Volumes": []
}
EOF

mkdir ${ebextensions_dir}

cat <<EOF >${ebextensions_dir}/${config_file}
{
 "option_settings" :
    [
      {
        "option_name" : "CLIENT_ID",
        "value" : "${CLIENT_ID}"
      },
      {
        "option_name" : "CLIENT_SECRET",
        "value" : "${CLIENT_SECRET}"
      },
      {
        "option_name" : "TOKEN_INFO_URI",
        "value" : "${TOKEN_INFO_URI}"
      }
    ]
}
EOF

## ZIP CONFIGS
zip config.zip ${docker_file} -r ${ebextensions_dir}

## COPY CONFIGS TO S3 BUCKET
aws s3 cp config.zip "s3://${CONFIGURATION_BUCKET}/config${TRAVIS_BUILD_NUMBER}.zip"
