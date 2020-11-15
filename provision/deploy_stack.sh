#!/bin/bash

set -eo pipefail

STACK_NAME="brewery"

command="create"
directory=$(cd "${BASH_SOURCE[0]%/*}" >/dev/null 2>&1 && pwd)

if aws cloudformation describe-stacks --stack-name $STACK_NAME &>/dev/null; then
  command="update"
fi

aws cloudformation $command-stack --stack-name $STACK_NAME \
  --template-body "file://$directory/cloudformation/master.yaml" \
  --parameter "file://$directory/cloudformation/values.json" \
  --capabilities CAPABILITY_IAM

aws cloudformation wait stack-${command}-complete \
  --stack-name "$STACK_NAME"
