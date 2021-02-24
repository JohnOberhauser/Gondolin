package ober.gondolin.common.database

import cc.popkorn.annotations.Injectable

@Injectable
class DatabaseManager {
    val driver = getCredentialsDriver("")
}