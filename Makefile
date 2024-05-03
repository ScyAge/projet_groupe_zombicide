# commandes
JAVAC = javac
JAVA = java
JAR = jar
JAVADOC = javadoc
JUNIT = java -jar junit-console.jar

# dossier
SRCDIR = src
TESTDIR = test
DOCDIR = docs
CLASSESDIR = classes

# les fichiers sources
SOURCES = $(wildcard $(SRCDIR)/zombicide/**/*.java) $(wildcard $(SRCDIR)/zombicide/*.java) $(wildcard $(SRCDIR)/zombicide/util/listchooser/NoneListChooser.java)

# les fichiers de tests
TESTS = $(wildcard $(TESTDIR)/zombicide/**/*.java) $(wildcard $(TESTDIR)/zombicide/*.java) $(wildcard $(TESTDIR)/zombicide/**/**/*.java) $(wildcard $(TESTDIR)/zombicide/**/**/**/*.java)

# les fichiers de classes
CLASSES = $(SOURCES:$(SRCDIR)/%.java=$(CLASSESDIR)/%.class)

all: compile jar tests#doc a mettre quand les erreurs seront fix

doc:
	@mkdir -p $(DOCDIR)
	$(JAVADOC) -sourcepath $(SRCDIR) -subpackages zombicide -d $(DOCDIR)

compile: $(CLASSES)

$(CLASSESDIR)/%.class: $(SRCDIR)/%.java
	@mkdir -p $(@D)
	$(JAVAC) -sourcepath $(SRCDIR) -d $(CLASSESDIR) $<

tests:
	$(JAVAC) -classpath junit-console.jar:$(CLASSESDIR) $(TESTS)
	$(JAVA) -jar junit-console.jar -classpath $(TESTDIR):$(CLASSESDIR) -scan-classpath

jar: compile
	$(JAR) cvfe livrable4_interactif.jar zombicide.GameMainInteractif -C $(CLASSESDIR) .
	$(JAR) cvfe livrable4_automatique.jar zombicide.GameMain -C $(CLASSESDIR) .

run_jar_interactif:
	$(JAVA) -jar livrable4_interactif.jar

run_jar_automatique:
	$(JAVA) -jar livrable4_automatique.jar

run_no_jar_interactif:
	$(JAVA) -classpath $(CLASSESDIR) zombicide.GameMainInteractif

run_no_jar_automatique:
	$(JAVA) -classpath $(CLASSESDIR) zombicide.GameMain

clean_tests:
	find $(TESTDIR) -name "*.class" -exec rm -f {} \;

clean: clean_tests
	rm -rf $(CLASSESDIR) $(DOCDIR) livrable4_interactif.jar livrable4_automatique.jar
