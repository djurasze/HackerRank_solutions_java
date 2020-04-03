--You are given a table, BST, containing two columns: N and P, where N represents the value of a node in Binary Tree, and P is the parent of N.
--
--
--
--Write a query to find the node type of Binary Tree ordered by the value of the node. Output one of the following for each node:
--
--Root: If node is root node.
--Leaf: If node is leaf node.
--Inner: If node is neither root nor leaf node.


SELECT N, 'Root' FROM BST WHERE P is NULL UNION
SELECT N, 'Inner' FROM BST WHERE N IN (SELECT P FROM BST) AND P IS NOT NULL UNION
SELECT N, 'Leaf' FROM BST WHERE N NOT IN (SELECT P FROM BST WHERE P IS NOT NULL) AND P IS NOT NULL
ORDER BY N;
