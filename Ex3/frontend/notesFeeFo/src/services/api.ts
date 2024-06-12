import axios from 'axios';

const API_URL = 'http://localhost:8080/';

const api = axios.create({
    headers: {"Access-Control-Allow-Origin": "*"},
    baseURL: API_URL,
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `${token}`;
    }
    return config;
});

export interface Note {
  id: number;
  title: string;
  content: string;
  clientId: number;
  createdAt?: string;
  modifiedAt?: string;
}

export interface AuthResponse {
  token: string;
}

export const login = async (username: string, password: string): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>('/auth/authenticate', { username, password });
  localStorage.setItem('token', response.data.token);
  return response.data;
};

export const getNotes = async (): Promise<Note[]> => {
  const response = await api.get<Note[]>('api/notes');
  return response.data;
};

export const addNote = async (note: Note): Promise<Note> => {
  const response = await api.post<Note>('api/notes', note);
  return response.data;
};

export const deleteNote = async (id: number): Promise<void> => {
  await api.delete(`api/notes/${id}`);
};

export const updateNote = async (id: number, note: Note): Promise<Note> => {
  const response = await api.put<Note>(`api/notes/${id}`, note);
  return response.data;
};

export default api;
