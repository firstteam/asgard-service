package io.choerodon.asgard.api.service

import io.choerodon.asgard.IntegrationTestConfiguration
import io.choerodon.asgard.api.service.impl.QuartzMethodServiceImpl
import io.choerodon.asgard.domain.QuartzMethod
import io.choerodon.asgard.infra.mapper.QuartzMethodMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class QuartzMethodServiceSpec extends Specification {

    QuartzMethodService quartzMethodService

    private QuartzMethodMapper mockMethodMapper = Mock(QuartzMethodMapper)

    void setup() {
        quartzMethodService = new QuartzMethodServiceImpl(mockMethodMapper)
    }

    def "CreateMethodList"() {
        given: "参数准备"
        def service = "service"
        def method01 = new QuartzMethod(method: "method01", id: 1L, code: "code", description: "description", objectVersionNumber: 1L)
        def method02 = new QuartzMethod(method: "method02", id: 1L, code: "code", description: "description", objectVersionNumber: 1L)
        def scanMethod01 = new QuartzMethod(method: "scanMethod01", id: 1L, code: "code", description: "description", objectVersionNumber: 1L)
        def dbMethods01 = new QuartzMethod(method: "dbSameMethod01", id: 1L, code: "code", description: "description", objectVersionNumber: 1L)

        def scanMethods = new ArrayList<QuartzMethod>()
        def dbMethods = new ArrayList<QuartzMethod>()

        scanMethods.add(method01)
        scanMethods.add(method02)
        scanMethods.add(scanMethod01)

        dbMethods.add(method01)
        dbMethods.add(method02)
        dbMethods.add(dbMethods01)

        and: "mock"
        mockMethodMapper.select(_) >> { return dbMethods }
        when: "方法调用"
        quartzMethodService.createMethodList(service, scanMethods)

        then: "无异常抛出"
        noExceptionThrown()

    }
}
