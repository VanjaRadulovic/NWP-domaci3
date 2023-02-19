export interface LoginRequest{
    email: string,
    password: string
}

export interface User{
    userId: number
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    permissions: Permission[]
}

export interface Permission{
    id: number,
    description: string
}

export interface Machine{
    id: number,
    name: string,
    status: string,
    working: boolean,
    date: string,
    user: User, 
    exists: boolean
}

export interface ErrorMessage{
    id: number,
    machineId: number,
    userId: number,
    date: string,
    errorType: string,
    errorMsg: string
}



