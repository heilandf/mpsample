package android.fheiland.com.mpsample.payment.repository

import android.fheiland.com.mpsample.payment.output.OutputEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 16/06/2018
 */
open class StorageRepository : StorageApi {
    private val firebaseInstance = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseInstance.reference.child("outputs")

    override fun storeOutputEntity(output: OutputEntity?) : Observable<Boolean> {
        output?.let {
            val uploadSubject = PublishSubject.create<Boolean>()
            val createdEntityReference = databaseReference.push()
            createdEntityReference.setValue(output).addOnCompleteListener { uploadSubject.onNext(it.isSuccessful) }
            return uploadSubject
        }
        return Observable.just(false)
    }

    override fun fetchEntities(): Observable<List<OutputEntity>> {
        val entitiesSubject = PublishSubject.create<List<OutputEntity>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val dataSource = ArrayList<OutputEntity>()
                for (child in p0.children) {
                    child.getValue(OutputEntity::class.java)?.let {
                        dataSource.add(it)
                    }
                }
                entitiesSubject.onNext(dataSource)
            }

            override fun onCancelled(p0: DatabaseError) = entitiesSubject.onNext(ArrayList())
        })

        return entitiesSubject
    }

}