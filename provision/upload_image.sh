#!/bin/bash
set -eo pipefail

## BUILD IMAGE
docker build -t "${APPLICATION_NAME}:${TRAVIS_BUILD_NUMBER}" .

## LOGIN ON ECR
ECR_LOGIN_CMD=$(aws ecr get-login --no-include-email --region eu-west-1)
eval "${ECR_LOGIN_CMD}"

## TAG IMAGE
docker tag "${APPLICATION_NAME}:${TRAVIS_BUILD_NUMBER}" "${AWS_ECR_URI}/${APPLICATION_NAME}:${TRAVIS_BUILD_NUMBER}"

## UPLOAD IMAGE
docker push "${AWS_ECR_URI}/${APPLICATION_NAME}:${TRAVIS_BUILD_NUMBER}"
