#!/bin/bash

set -eo pipefail

pip install --user awscli
export PATH=$PATH:$HOME/.local/bin