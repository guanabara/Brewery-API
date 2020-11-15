#!/bin/bash

set -eo pipefail

aws elasticbeanstalk create-application-version \
  --application-name "${APPLICATION_NAME}" \
  --version-label "${TRAVIS_BUILD_NUMBER}" \
  --source-bundle S3Bucket="${CONFIGURATION_BUCKET}",S3Key=config"${TRAVIS_BUILD_NUMBER}".zip

aws elasticbeanstalk update-environment \
  --environment-name "${APPLICATION_ENVIRONMENT}" \
  --application-name "${APPLICATION_NAME}" \
  --version-label "${TRAVIS_BUILD_NUMBER}"
