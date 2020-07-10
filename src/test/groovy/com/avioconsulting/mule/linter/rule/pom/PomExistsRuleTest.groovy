package com.avioconsulting.mule.linter.rule.pom

import com.avioconsulting.mule.linter.model.Application
import com.avioconsulting.mule.linter.model.Rule
import com.avioconsulting.mule.linter.model.RuleViolation
import com.avioconsulting.mule.linter.TestApplication
import spock.lang.Specification

class PomExistsRuleTest extends Specification {

    private final TestApplication testApp = new TestApplication()

    def cleanup() {
        testApp.remove()
    }

    @SuppressWarnings(['MethodName', 'MethodReturnTypeRequired'])
    def 'Maven Pom Exists check'() {
        given:
        testApp.addPom()
        Rule rule = new PomExistsRule()

        when:
        Application app = new Application(testApp.appDir)
        List<RuleViolation> violations = rule.execute(app)

        then:
        violations.size() == 0
    }

    @SuppressWarnings(['MethodName', 'MethodReturnTypeRequired'])
    def 'Maven Pom Does not exist check'() {
        given:
        Rule rule = new PomExistsRule()

        when:
        Application app = new Application(testApp.appDir)
        List<RuleViolation> violations = rule.execute(app)

        then:
        violations.size() == 1
    }
}
