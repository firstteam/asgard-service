package script.db

databaseChangeLog(logicalFilePath: 'asgard_saga_instance.groovy') {
    changeSet(id: '2018-07-04-create-table-asgard_saga_instance', author: 'jcalaz@163.com') {
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'ASGARD_SAGA_INSTANCE_S', startValue:"1")
        }
        createTable(tableName: "ASGARD_SAGA_INSTANCE") {
            column(name: 'ID', type: 'BIGINT UNSIGNED', remarks: 'ID', autoIncrement: true) {
                constraints(primaryKey: true, primaryKeyName: 'PK_ASGARD_SAGA_INSTANCE')
            }
            column(name: 'SAGA_CODE', type: 'VARCHAR(64)', remarks: 'saga标识') {
                constraints(nullable: false)
            }
            column(name: 'STATUS', type: 'VARCHAR(32)', defaultValue: "RUNNING", remarks: 'saga执行状态。RUNNING,ROLLBACK,FAILED,NON_CONSUMER,COMPLETED') {
                constraints(nullable: false)
            }
            column(name: 'REF_TYPE', type: 'VARCHAR(128)', remarks: '关联类型')
            column(name: 'REF_ID', type: 'TEXT', remarks: '关联id')
            column(name: 'INPUT_DATA_ID', type: 'BIGINT UNSIGNED', remarks: '输入参数的json data id')
            column(name: 'OUTPUT_DATA_ID', type: 'BIGINT UNSIGNED', remarks: '输出参数的json data id')
            column(name: "START_TIME", type: "DATETIME(3)", remarks: 'saga开始执行的时间')
            column(name: "END_TIME", type: "DATETIME(3)", remarks: 'saga执行结束的时间')


            column(name: "OBJECT_VERSION_NUMBER", type: "BIGINT", defaultValue: "1")
            column(name: "CREATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "CREATION_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "LAST_UPDATE_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}
