# Issue Templates

Use these templates when the task needs structured GitHub issue content, a progress update, or metadata guidance.

## New Issue Template

Title pattern:

`<problem or gap> in <area>`

Body template:

```md
## Summary
<One short paragraph describing the problem and why it matters.>

## Current Behavior
- <Observed behavior>
- <Where it appears>

## Expected Outcome
- <Target behavior or completed outcome>

## Impact
- <Who or what is affected>

## Proposed Scope
- <What this issue should include>
- <What this issue should not include>

## Acceptance Criteria
- [ ] <Criterion 1>
- [ ] <Criterion 2>
- [ ] <Criterion 3>

## Notes
- <Constraints, links, PRs, logs, commits, or implementation hints>
```

## Progress Update Template

```md
## Progress Update

Completed:
- <What changed>

Remaining:
- <What is left>

Blockers/Risks:
- <Current blocker or `None`>

Next Step:
- <Single concrete next action>
```

## Improvement / Fix Proposal Template

```md
## Suggested Fix

### Root Cause
- <Confirmed cause or best current inference>

### Proposed Change
1. <Step one>
2. <Step two>
3. <Step three>

### Risks
- <Tradeoff or dependency>

### Validation
- <How to verify the fix>
```

## Metadata Update Template

```md
## Metadata Update

Labels:
- Add: <labels to add or `None`>
- Remove: <labels to remove or `None`>

Assignees:
- Add: <assignees to add or `None`>
- Remove: <assignees to remove or `None`>

Milestone:
- Set to: <milestone name or `None`>

Reason:
- <Why the metadata change improves triage or execution>
```

## Triage Rubric

Use this rubric when reviewing existing issues:

- `clear`: problem, impact, and done-state are explicit
- `actionable`: a contributor could start work without guessing the goal
- `sized`: scope is narrow enough for one issue
- `owned`: assignee or next responsible party is known, if the team uses ownership
- `current`: status reflects reality and blockers are visible
- `routed`: labels and milestone, when used by the repository, match the issue’s actual category and target

If an issue fails more than one rubric item, recommend updating it before implementation starts.
