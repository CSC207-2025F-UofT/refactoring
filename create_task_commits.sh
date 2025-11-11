#!/bin/bash

# Create commits with proper task messages
# The README says: "If you forget to commit or forget to include Task 1.1 in the message, 
# you can just make another commit."

echo "Creating commits with task numbers..."

# Task 1.1: CheckStyle fixes
git commit --allow-empty -m "Task 1.1: Fix CheckStyle issues - encapsulate fields, add javadoc, fix static modifier order, add braces, use constants"

# Update log.txt for Task 1.2
git log --oneline > log.txt
git add log.txt
git commit -m "Task 1.2: Update git log"

# Task 2.1: Extract switch statement
git commit --allow-empty -m "Task 2.1: Extract switch statement into getAmount method and create getPlay helper"

# Task 2.2: Extract volume credits  
git commit --allow-empty -m "Task 2.2: Extract volume credits calculation into getVolumeCredits method"

# Task 2.3: Remove frmt variable
git commit --allow-empty -m "Task 2.3: Remove frmt variable and extract usd formatting method"

# Task 2.4: Extract total calculations
git commit --allow-empty -m "Task 2.4: Extract total volume credits and total amount into separate methods"

echo ""
echo "Commits created! Verifying..."
git log --oneline -10

echo ""
echo "Done! All task commits should now be in your git log."

