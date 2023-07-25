#!/bin/bash

calculate_factorial() {
  local num=$1
  if ((num == 0 || num == 1)); then
    result=1
  else
    result=1
    for ((i = 2; i <= num; i++)); do
      result=$((result * i))
    done
  fi
}

perform_calculation() {
  case "$operator" in
    "+") result=$((operand1 + operand2));;
    "-") result=$((operand1 - operand2));;
    "*") result=$((operand1 * operand2));;
    "/") result=$((operand1 / operand2));;
    "%") result=$((operand1 % operand2));;
    "!") calculate_factorial $operand1;;
    *) echo "Invalid operator. Please enter a valid operator (+, -, *, /, %, or !)"; exit 1;;
  esac
}

read -p "Enter the operator (+, -, *, /, %, or !): " operator

if [ "$operator" != "!" ]; then
  read -p "Enter the first operand: " operand1
  read -p "Enter the second operand: " operand2

  # Check if the operands are valid numbers
  if ! [[ "$operand1" =~ ^[+-]?[0-9]+([.][0-9]+)?$ && "$operand2" =~ ^[+-]?[0-9]+([.][0-9]+)?$ ]]; then
    echo "Invalid input. Please enter valid numeric operands."
    exit 1
  fi

  perform_calculation
else
  read -p "Enter the operand: " operand1

  # Check if the operand is a valid number
  if ! [[ "$operand1" =~ ^[+-]?[0-9]+([.][0-9]+)?$ ]]; then
    echo "Invalid input. Please enter a valid numeric operand."
    exit 1
  fi

  perform_calculation
fi

echo "Result: $result"

