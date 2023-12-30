interface PetDTO {
    petId?: number;
    name: string;
    species: string;
    age: number;
    gender?: string;
    description?: string;
    breed: string;
    houseTraining?: boolean;
    behaviour?: Behavior;
    healthStatus?: HealthStatus;
    shelterId?: number;
    images?: string[]
}