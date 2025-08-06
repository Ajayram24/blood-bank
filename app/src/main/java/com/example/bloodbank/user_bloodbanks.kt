class BloodBankModel(
    var name: String, var distance: String, var phone: String,
    var openHours: String, var rating: Double, // 8 types: A+, A-, B+, B-, O+, O-, AB+, AB-
    var bloodAvailability: BooleanArray
)