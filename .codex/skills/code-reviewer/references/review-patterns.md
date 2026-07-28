# Review Patterns

Use these patterns to keep issue-based review concrete and traceable.

## Review Plan Pattern

Use when setting the review scope:

```md
Review plan:
1. Read the issue and definition of done.
2. Inspect the implementation changes.
3. Compare tests, README, and Postman assets to the required outcome.
4. Report findings in severity order.
```

## Issue Comparison Pattern

Use when mapping the issue to the code:

```md
Issue-to-code check:
- Required behavior: <requirement>
- Implemented behavior: <what the code does>
- Status: <satisfied/partial/missing/conflicting>
- Evidence: <files, tests, or behavior>
```

## Finding Pattern

Use when raising a review finding:

```md
Finding: <short title>
- Severity: <high/medium/low>
- Gap: <what is missing or wrong>
- Why it matters: <impact or contract violation>
- Evidence: <file, test, or behavior>
- Expected fix: <specific correction>
```

## Fix Request Pattern

Use when asking `$issue-developer` to address the review:

```md
Please address these in-scope review findings for issue <issue number>:
1. <required fix 1>
2. <required fix 2>

Re-run the relevant tests and report whether README or Postman assets required updates.
```

## Disagreement Escalation Pattern

Use when reviewer and developer do not agree:

```md
There is a review disagreement that needs a user decision.

- Issue requirement: <what the ticket or definition of done says>
- Reviewer position: <why the change is required>
- Developer position: <why they believe it is not required or should be done differently>
- Confirmed evidence: <facts>
- Decision needed: <the exact point the user should resolve>
```

## Review Completion Pattern

Use when closing the review:

```md
Review completion check:
- Issue scope satisfied: <yes/no>
- Definition of done satisfied: <yes/no>
- Findings remaining: <list or none>
- Tests verified: <list>
- README updated: <yes/no/not needed>
- Postman updated: <yes/no/not needed>
- Disagreements escalated: <yes/no>
```
