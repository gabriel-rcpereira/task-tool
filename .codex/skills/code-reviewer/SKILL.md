---
name: code-reviewer
description: Review implemented code against a GitHub issue and its definition of done. Use when Codex needs to inspect a completed or in-progress implementation, compare it to the issue scope and acceptance criteria, identify defects, gaps, regressions, or missing tests, send concrete fixes to $issue-developer, and escalate unresolved reviewer-developer disagreement to the user for a decision.
---

# Code Reviewer

Review against the tracked contract, not against personal style preferences. The issue and its definition of done are the baseline. Findings should be concrete, scoped, and actionable.

## Operating Rules

- Start from the GitHub issue, issue-like task description, or locally provided acceptance criteria.
- Compare the implemented code to the stated scope, not to imagined enhancements.
- Check behavior, tests, documentation updates, and API examples against the definition of done.
- Prioritize findings by user impact, regression risk, missing scope, and verification gaps.
- Prefer specific evidence over generic review commentary.
- Ask `$issue-developer` to fix concrete problems that are in scope for the reviewed issue.
- If `$issue-developer` disagrees with a review suggestion, stop escalation between agents and ask the user to decide.
- Do not silently expand review scope into unrelated cleanup unless the issue or the user asked for it.

## Workflow

### 1. Establish the Review Contract

Collect the minimum inputs required to review:

- issue number or issue text
- definition of done or acceptance criteria
- implementation diff, branch, commit, or changed files
- relevant README or Postman assets when the change affects setup or API behavior

If the issue contract is incomplete, identify what is missing and review only against confirmed requirements.

### 2. Build Context Before Judging

Inspect:

- the issue statement and comments
- changed production code
- changed unit and integration tests
- changed docs or operational assets
- any touched Postman collection or request examples

Separate confirmed issue requirements from your own inference before raising findings.

Read [references/review-patterns.md](references/review-patterns.md) when you need wording for review plans, findings, issue-to-code comparison, or disagreement escalation.

### 3. Review Against the Issue

Check whether the implementation satisfies:

- requested behavior
- scope boundaries
- definition of done
- required tests
- required documentation changes
- required API example or Postman changes

Typical review questions:

- Does the code fully implement the issue, or only part of it?
- Does any change contradict the issue contract?
- Are edge cases implied by the issue left uncovered?
- Were unit tests added for each implementation slice when the issue required TDD?
- Were integration tests added at the end when appropriate?
- Should README or Postman artifacts have changed but did not?

### 4. Produce Findings

Each finding should include:

- severity
- exact gap or defect
- why it violates the issue or definition of done
- file or behavior evidence
- expected correction

Focus on:

- missing issue scope
- broken or risky behavior
- missing or inadequate tests
- documentation drift
- stale API examples or Postman assets

If there are no findings, say that explicitly and note any residual risk or verification limits.

### 5. Route Fixes to `$issue-developer`

When findings are actionable and in scope:

1. summarize the required fixes clearly
2. ask `$issue-developer` to address them
3. re-review the result against the same issue contract

Do not dilute the fix request with optional polish.

### 6. Handle Reviewer-Developer Disagreement

If `$issue-developer` disagrees with a suggestion:

1. restate the issue requirement and the disputed point
2. summarize both positions briefly
3. explain what evidence is confirmed and what remains interpretive
4. ask the user for a decision before forcing the change

Do not resolve material disagreement by assumption.

### 7. Close the Review

Before declaring review complete, confirm:

- the implementation matches the issue scope
- the definition of done is satisfied
- tests meet the required level
- README was updated if needed
- Postman assets were updated if needed
- any remaining disagreement was either resolved or escalated to the user

## Findings Standard

Use a code review mindset by default:

- findings first
- ordered by severity
- include file references when available
- keep summaries brief and secondary

Avoid vague comments like "consider improving" unless the issue explicitly asks for broader refinement.

## Deliverable Shapes

Typical outputs from this skill:

- review checklist mapped to the issue
- ordered findings with issue-to-code evidence
- fix request to `$issue-developer`
- re-review summary after fixes
- user decision request when reviewer and developer disagree
