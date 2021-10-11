package bookmyconsultation.appointmentservice.entity;

public class PrescriptionMedicineEntity {

    private String name;

    private String type;

    private String dosage;

    private String duration;

    private String frequency;

    private String remarks;

    public PrescriptionMedicineEntity() {
    }

    public PrescriptionMedicineEntity(String name, String type, String dosage, String duration, String frequency, String remarks) {
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        this.duration = duration;
        this.frequency = frequency;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                " | type='" + type + '\'' +
                " | dosage='" + dosage + '\'' +
                " | duration='" + duration + '\'' +
                " | frequency='" + frequency + '\'' +
                " | remarks='" + remarks + '\'' +
                "}";
    }
}