interface PetDTO {
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
}