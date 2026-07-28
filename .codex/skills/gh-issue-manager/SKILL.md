---
name: gh-issue-manager
description: Create, triage, document, and advance GitHub issues. Use when Codex needs to turn work into issues, refine issue scope, add progress updates, review stale or blocked issues, suggest fixes or improvements, or manage issue state, labels, assignees, and milestones for a repository through GitHub tools or gh.
---

# GitHub Issue Manager

Manage GitHub issues as tracked work, not as loose notes. Create issues with clear outcomes, keep progress visible, maintain routing metadata, and convert investigation into actionable next steps.

## Quick Start

1. Confirm the target repository and current objective.
2. Inspect existing issues, labels, milestones, assignees, and related PRs before creating duplicates.
3. Choose the operation:
   - create a new issue
   - improve an existing issue
   - post a progress update
   - triage or reprioritize a set of issues
   - suggest fixes or implementation steps
   - update labels, assignees, or milestone
4. Use GitHub app tools first when available. Use `gh` only as a fallback.
5. End with a concrete status: created, updated, blocked, ready, closed, or reprioritized.

## Workflow

### 1. Build Context

Start by collecting only the context needed to act:

- repository name
- issue number, if one already exists
- related PR, commit, or failing behavior
- current status, labels, assignees, and milestone if relevant
- any team conventions for issue titles, labels, or ownership

If the request is broad, reduce it to a clear work unit before mutating anything.

### 2. Choose the Right Action

Create a new issue when:

- the work does not already exist
- the problem statement and expected outcome are clear enough to track
- the user wants backlog capture, follow-up work, or a defect documented

Improve an existing issue when:

- the issue exists but is vague, stale, or missing acceptance criteria
- investigation uncovered root cause, constraints, or better next steps
- progress happened off-platform and needs to be recorded

Post a progress update when:

- implementation is underway
- a blocker appeared
- scope changed
- a decision was made that future contributors need to see

Suggest fixes or improvements when:

- the issue lacks a credible path forward
- the problem needs decomposition into smaller steps
- the user wants implementation guidance without immediately changing code

Update issue metadata when:

- the issue should move into a milestone
- ownership should be made explicit with assignees
- labels can improve triage, priority, or discoverability
- stale metadata no longer matches reality

### 3. Create Strong Issues

When creating an issue, include:

- concise title with the problem, not a vague activity
- current behavior or gap
- expected behavior or outcome
- impact on users, developers, or delivery
- proposed scope
- acceptance criteria or definition of done
- implementation notes only when they reduce ambiguity

Prefer one issue per independently completable outcome. Split oversized issues instead of hiding multiple deliverables in one ticket.

Read [references/issue-templates.md](references/issue-templates.md) when drafting titles, bodies, progress comments, metadata recommendations, or fix plans.

### 4. Manage Progress Explicitly

When updating issue progress, record:

- what changed
- what remains
- blockers or risks
- owner, if known
- next concrete step

Do not post empty status noise. Every update should reduce uncertainty for the next person reading the issue.

### 5. Manage Labels, Assignees, and Milestones Deliberately

Use metadata to improve execution, not just to decorate issues.

Labels:

- add labels that clarify type, area, priority, or status
- remove labels that conflict with the current issue state
- prefer a small consistent set over many overlapping labels

Assignees:

- assign only when ownership is known or explicitly requested
- avoid adding speculative owners
- if no owner is known, state that clearly instead of guessing

Milestones:

- add a milestone only when the issue belongs to a real delivery target
- move or clear milestones when scope or schedule changed
- do not invent milestone meaning; infer only from repository conventions or explicit user instruction

If repository label or milestone conventions are unclear, inspect existing issues first and match the established pattern.

### 6. Suggest Fixes Pragmatically

When suggesting improvements or fixes:

- tie the suggestion to the issue’s actual failure mode or gap
- prefer the smallest credible fix first
- call out tradeoffs, risks, and dependencies
- distinguish confirmed facts from inference
- convert vague recommendations into ordered implementation steps when possible

If the problem spans code and process, separate them clearly.

## Mutation Rules

- Never create duplicate issues without stating why a new issue is justified.
- Never silently rewrite issue scope; preserve intent and explain significant changes.
- Never close an issue unless the user asked for it or the evidence clearly shows it is resolved and closure is part of the requested action.
- Never add labels, assignees, or milestones based on guesswork when repository conventions are unclear.
- If repository access is missing, stop at a draft recommendation or local note.

## Tool Guidance

Prefer GitHub app tools for:

- fetching repository metadata
- searching issues and pull requests
- creating issues
- reading comments and issue state
- updating labels, assignees, and milestones when the available tools support it

Use `gh` as fallback for:

- workflows not covered by the app tools
- repository auth checks
- commands where local git context materially helps
- issue metadata updates that are missing from the app tools

If both are available, avoid duplicating the same lookup in both systems unless verification is needed.

## Deliverable Shapes

Typical outputs from this skill:

- new GitHub issue with title and structured body
- updated issue description with sharper scope and acceptance criteria
- progress comment with blockers and next steps
- triage summary for multiple issues
- metadata update plan for labels, assignees, or milestone
- fix proposal mapped to an issue

Always report the exact issue number or issue set that changed.
