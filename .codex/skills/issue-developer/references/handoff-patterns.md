# Handoff Patterns

Use these patterns to keep issue-driven implementation disciplined.

## Clarification Prompt Pattern

Use when ambiguity is material:

```md
Before implementing this, I need to confirm:
- <question 1>
- <question 2>

These answers affect:
- <behavior or contract impacted>
```

## TDD Checkpoint Pattern

Use when summarizing progress during implementation:

```md
TDD checkpoint:
- Added failing test for: <behavior>
- Failure confirmed: <why it failed>
- Minimal implementation added: <change>
- Current verification: <tests run>
```

## Follow-up Issue Escalation Pattern

Use when a new problem is discovered that should not be folded into the current issue:

```md
I found a separate issue while implementing this task:
- Problem: <what is wrong>
- Impact: <why it matters>
- Reason to separate: <why it should not expand current scope>

Use $gh-issue-manager to document and create a follow-up issue for this.
```

## Done Check Pattern

Use before closing implementation work:

```md
Issue completion check:
- Scope satisfied: <yes/no and why>
- Tests added or updated: <list>
- Tests run: <list>
- Residual risks: <if any>
- Follow-up issues created: <issue numbers or none>
```
