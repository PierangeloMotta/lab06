# Esercizio di risoluzione di un merge conflict

**Il tempo massimo in laboratorio per questo esercizio è di _20 minuti_.
Se superato, sospendere l'esercizio e riprenderlo per ultimo!**

Si visiti https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.
Questo repository contiene due branch: `master` e `feature`

Per ognuna delle seguenti istruzioni, si annoti l'output ottenuto.
Prima di eseguire ogni operazione sul worktree o sul repository,
si verifichi lo stato del repository con `git status`.

1. Si cloni localmente il repository
https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test
Cloning into '.'...
remote: Enumerating objects: 12, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 12 (delta 1), reused 1 (delta 1), pack-reused 8
Unpacking objects: 100% (12/12), 1.33 KiB | 136.00 KiB/s, done.

git status 
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean

2. Ci si assicuri di avere localmente entrambi i branch remoti	

git remote show origin
* remote origin
  Fetch URL: https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test
  Push  URL: https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test
  HEAD branch: master
  Remote branches:
    feature tracked
    master  tracked
  Local branch configured for 'git pull':
    master merges with remote master
  Local ref configured for 'git push':
    master pushes to master (up to date)
    

3. Si faccia il merge di `feature` dentro `master`, ossia: si posizioni la `HEAD` su `master`
   e da qui si esegua il merge di `feature`
   git log --oneline --graph
* 8e0f29c (HEAD -> master, origin/master, origin/HEAD) Change HelloWorld to print the number of available processors
* d956df6 Create .gitignore
* 700ee0b Create HelloWorld

   git merge feature 
Auto-merging HelloWorld.java
CONFLICT (content): Merge conflict in HelloWorld.java
Automatic merge failed; fix conflicts and then commit the result.

git diff
diff --cc HelloWorld.java
index 4f5d9b7,ed370d1..0000000
--- a/HelloWorld.java
+++ b/HelloWorld.java
@@@ -1,11 -1,9 +1,17 @@@
  public final class HelloWorld {
  
+       private static final String AUTHOR = "Danilo Pianini";
+ 
        public static void main(final String[] args) {
++<<<<<<< HEAD
 +              System.out.println("This program is running in a PC with " + procNumber() + " logic processors!");
 +      }
 +
 +      public static int procNumber() {
 +              return Runtime.getRuntime().availableProcessors();
++=======
+               System.out.println("This program has been realised by " + AUTHOR);
++>>>>>>> feature
        }
  
  }


4. Si noti che viene generato un **merge conflict**!
git status 
On branch master
Your branch is up to date with 'origin/master'.

You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)
        both modified:   HelloWorld.java

no changes added to commit (use "git add" and/or "git commit -a")


5. Si risolva il merge conflict come segue:
   - Il programma Java risultante deve stampare sia il numero di processori disponibili
     (funzionalità presente su `master`)
     che il nome dell'autore del file
     (funzionalità presente su `feature`)

cat HelloWorld.java 
public final class HelloWorld {

        private static final String AUTHOR = "Danilo Pianini";

        public static void main(final String[] args) {
                System.out.println("This program is running in a PC with " + procNumber() + " logic processors!");
                System.out.println("This program has been realised by " + AUTHOR);
        }

        public static int procNumber() {
                return Runtime.getRuntime().availableProcessors();
        }

}

     
6. Si crei un nuovo repository nel proprio github personale
7. Si aggiunga il nuovo repository creato come **remote** e si elenchino i remote
git remote -v
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test (fetch)
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test (push)

git remote remove origin
git remote -v

git remote add origin https://github.com/PierangeloMotta/lab06-61.git
git remote -v
origin  https://github.com/PierangeloMotta/lab06-61.git (fetch)
origin  https://github.com/PierangeloMotta/lab06-61.git (push)

git status 
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)
        both modified:   HelloWorld.java

no changes added to commit (use "git add" and/or "git commit -a")

git add HelloWorld.java
git status 
On branch master
All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)

Changes to be committed:
        modified:   HelloWorld.java


git commit -m "Merged feature branch: new Author name"
[master 9cab899] Merged feature branch: new Author name

git log --oneline --graph
*   9cab899 (HEAD -> master) Merged feature branch: new Author name
|\  
| * bed943f (feature) Print author information
* | 8e0f29c Change HelloWorld to print the number of available processors
|/  
* d956df6 Create .gitignore
* 700ee0b Create HelloWorld


8. Si faccia push del branch `master` sul proprio repository

git push origin master 
Enumerating objects: 15, done.
Counting objects: 100% (15/15), done.
Compressing objects: 100% (13/13), done.
Writing objects: 100% (15/15), 1.57 KiB | 1.57 MiB/s, done.
Total 15 (delta 4), reused 0 (delta 0)
remote: Resolving deltas: 100% (4/4), done.
To https://github.com/PierangeloMotta/lab06-61.git
 * [new branch]      master -> master

9. Si setti il branch remoto `master` del nuovo repository come *upstream* per il proprio branch `master` locale

git push -u origin master 
Branch 'master' set up to track remote branch 'master' from 'origin'.
Everything up-to-date
