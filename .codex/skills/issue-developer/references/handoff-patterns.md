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

## Iteration Plan Pattern

Use when splitting the issue into small work items:

```md
Implementation slices:
1. <small behavior slice 1>
2. <small behavior slice 2>
3. <small behavior slice 3>

Each slice will follow: unit test -> production change -> verification.
Integration coverage will be added after the slices are complete.
```

## TDD Checkpoint Pattern

Use when summarizing progress during implementation:

```md
TDD checkpoint:
- Current slice: <behavior>
- Added failing unit test for: <behavior>
- Failure confirmed: <why it failed>
- Minimal implementation added: <change>
- Current verification: <tests run>
```

## Integration Check Pattern

Use after the unit-test-driven slices are complete:

```md
Integration checkpoint:
- Integrated behavior covered: <workflow>
- Integration tests added or updated: <tests>
- Broader verification: <tests run>
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
- Unit tests added or updated: <list>
- Integration tests added or updated: <list or none>
- Tests run: <list>
- README updated: <yes/no>
- Postman updated: <yes/no>
- Residual risks: <if any>
- Follow-up issues created: <issue numbers or none>
```
