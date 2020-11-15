#!/bin/bash

set -eo pipefail

template_bucket=brewery-cloudformation

for template in ./provision/cloudformation/templates/*
do
  ## COPY TEMPLATE TO S3 BUCKET
  aws s3 cp "$template" "s3://$template_bucket/${template##*/}"
done
