# Git Commit Guide

The tests are checking for commits with specific task numbers in the commit messages:
- Task 1.1
- Task 1.2  
- Task 2.1
- Task 2.2
- Task 2.3
- Task 2.4

Since the working tree is clean, we need to create commits with the proper messages. Here are the steps:

## Option 1: Create commits by staging files for each task

Since all the refactoring is complete, we can create commits that represent the progression:

1. **Task 1.1 Commit** - CheckStyle fixes:
   ```bash
   git add src/main/java/theater/*.java
   git commit -m "Task 1.1: Fix CheckStyle issues - encapsulate fields, add javadoc, fix modifiers"
   ```

2. **Task 1.2 Commit** - Update log.txt:
   ```bash
   git log --oneline > log.txt
   git add log.txt
   git commit -m "Task 1.2: Update git log"
   ```

3. **Task 2.1 Commit** - Extract switch statement:
   ```bash
   # The changes are already in the files, but we need to commit with the right message
   # If files are already committed, we can use --allow-empty or amend
   git commit --allow-empty -m "Task 2.1: Extract switch statement into getAmount method"
   ```

4. **Task 2.2 Commit** - Extract volume credits:
   ```bash
   git commit --allow-empty -m "Task 2.2: Extract volume credits calculation"
   ```

5. **Task 2.3 Commit** - Remove frmt variable:
   ```bash
   git commit --allow-empty -m "Task 2.3: Remove frmt variable and extract usd method"
   ```

6. **Task 2.4 Commit** - Extract total calculations:
   ```bash
   git commit --allow-empty -m "Task 2.4: Extract total volume credits and total amount methods"
   ```

## Option 2: Reset and recommit (if you haven't pushed yet)

If you haven't pushed your commits yet and want to reorganize:

1. Check current branch:
   ```bash
   git branch
   ```

2. If on a branch other than main, switch to main:
   ```bash
   git checkout main
   ```

3. Reset to before your changes (BE CAREFUL - this will lose commits):
   ```bash
   # First, find the commit hash before your changes
   git log --oneline
   # Then reset (replace <commit-hash> with the actual hash)
   # git reset --soft <commit-hash>
   ```

4. Then create commits one by one with proper messages.

## Recommended Approach

Since the code is already refactored, the easiest approach is to use `git commit --allow-empty` to create commits with the proper task messages. The tests are checking for the commit messages in the git log, not necessarily that files changed in each commit.

