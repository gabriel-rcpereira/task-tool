---
name: issue-developer
description: Implement GitHub issues with strong scope control and test-first discipline. Use when Codex receives an issue or issue-like task and needs to understand the project, clarify ambiguous requirements, avoid assumptions, implement through TDD, and escalate newly discovered follow-up work to $gh-issue-manager for documentation and issue creation.
---

# Issue Developer

Treat an issue as a bounded engineering contract. Understand the requested outcome, confirm what is unknown, implement with tests leading the change, and surface newly discovered work instead of quietly absorbing it.

## Operating Rules

- Start from the issue scope, not from preferred implementation ideas.
- Ask for clarification whenever the issue, acceptance criteria, or expected behavior are ambiguous enough to change the implementation.
- Avoid assumptions about business rules, API contracts, schema intent, or backward compatibility.
- Prefer small, reviewable changes that satisfy the stated issue before stretching into adjacent cleanup.
- If new work is discovered that should not be folded into the current task, ask `$gh-issue-manager` to document it and create or update the relevant GitHub issue.

## Workflow

### 1. Understand the Task

When the issue is provided:

1. Restate the target behavior in concrete terms.
2. Extract explicit scope, constraints, and acceptance criteria.
3. Separate facts from inferences.
4. Identify missing decisions or conflicting signals.

Ask clarifying questions before editing code when any of these are unclear:

- expected external behavior
- allowed tradeoffs
- migration strategy
- compatibility requirements
- success criteria

If the user does not answer and the ambiguity is material, stop and ask. Do not guess.

### 2. Build Project Context

Inspect the relevant code paths before proposing changes:

- entrypoints and user-facing contracts
- service or domain logic
- persistence and configuration
- existing tests and test style

Understand the current architecture and follow existing patterns unless the issue explicitly requires changing them.

### 3. Work in TDD Order

Default sequence:

1. write or update a failing test for the desired behavior
2. run the focused test and confirm it fails for the right reason
3. implement the smallest change that makes it pass
4. run the focused tests again
5. run broader relevant tests once the change stabilizes
6. refactor only while tests stay green

If test-first is temporarily blocked by missing harness or infrastructure, explain the blocker and get as close as possible by creating the test shape first.

### 4. Control Scope During Implementation

Keep current-issue work separate from newly discovered problems.

Examples of follow-up work that should usually become separate issues:

- unrelated bugs found while tracing the issue
- larger refactors required only for code health
- infrastructure or migration work beyond the current acceptance criteria
- documentation gaps not required to complete the change

When this happens:

- note the finding clearly
- do not silently expand the current change unless the user approves
- ask `$gh-issue-manager` to draft or create the follow-up issue

### 5. Validate Before Closeout

Before declaring the issue done:

- confirm the implementation matches the issue scope
- confirm tests cover the new or changed behavior
- note any residual risks or follow-up items
- distinguish what was verified from what remains assumed

## Clarification Triggers

Ask the user instead of assuming when you encounter:

- vague words like "support", "improve", or "fix" without clear expected behavior
- a response contract change that could break consumers
- competing implementation directions with materially different tradeoffs
- migration steps that could affect data or deployment
- missing acceptance criteria for a user-visible feature

## Issue Escalation Rule

If you find a legitimate new issue while implementing:

1. summarize the problem, impact, and why it is out of scope
2. invoke `$gh-issue-manager` to document it
3. create or update the GitHub issue before forgetting the context, when repository permissions allow
4. continue only on the original issue unless the user redirects scope

Read [references/handoff-patterns.md](references/handoff-patterns.md) when you need wording for clarification requests, TDD checkpoints, or follow-up issue escalation.

## Deliverable Shapes

Typical outputs from this skill:

- clarification questions before implementation
- scoped implementation plan tied to the issue
- failing test then code change workflow
- concise summary of completed work, tests run, and remaining risks
- handoff to `$gh-issue-manager` for discovered follow-up issues
