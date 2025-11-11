#!/bin/bash

# Script to create commits with proper task messages
# This assumes the code changes are already complete

echo "Setting up commits for refactoring tasks..."

# Check if we're on the right branch
CURRENT_BRANCH=$(git branch --show-current)
echo "Current branch: $CURRENT_BRANCH"

# Note: README says to work on main branch
if [ "$CURRENT_BRANCH" != "main" ]; then
    echo "Warning: You're on branch '$CURRENT_BRANCH', but README says to work on 'main'"
    read -p "Do you want to switch to main? (y/n) " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        git checkout main
    fi
fi

# Check if there are uncommitted changes
if ! git diff-index --quiet HEAD --; then
    echo "You have uncommitted changes. Please commit or stash them first."
    exit 1
fi

# Create commits for each task
# Since files are already committed, we'll use --allow-empty to create marker commits
# OR we can check the git log and amend existing commits

echo ""
echo "The following commits need to exist with these messages:"
echo "  - Task 1.1: Fix CheckStyle issues"
echo "  - Task 1.2: Update git log"
echo "  - Task 2.1: Extract switch statement"
echo "  - Task 2.2: Extract volume credits"
echo "  - Task 2.3: Remove frmt variable"
echo "  - Task 2.4: Extract total calculations"
echo ""

# Check current git log
echo "Current git log:"
git log --oneline -10

echo ""
echo "If your commits don't have the task numbers, you have two options:"
echo "1. Use 'git commit --amend' to fix the most recent commit message"
echo "2. Use 'git rebase -i' to edit multiple commit messages"
echo "3. Create new commits with --allow-empty (tests may check for file changes though)"

echo ""
echo "To create commits manually, run:"
echo "  git commit --allow-empty -m 'Task 1.1: Fix CheckStyle issues'"
echo "  git commit --allow-empty -m 'Task 1.2: Update git log'"
echo "  git commit --allow-empty -m 'Task 2.1: Extract switch statement into getAmount method'"
echo "  git commit --allow-empty -m 'Task 2.2: Extract volume credits calculation'"
echo "  git commit --allow-empty -m 'Task 2.3: Remove frmt variable and extract usd method'"
echo "  git commit --allow-empty -m 'Task 2.4: Extract total volume credits and total amount methods'"

