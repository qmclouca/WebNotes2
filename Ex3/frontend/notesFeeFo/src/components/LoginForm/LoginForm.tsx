
import React, { useState } from 'react';
import { login } from '../../services/api';
import { useAuth } from '../../AuthContext';
import { useNavigate } from 'react-router-dom';


const LoginForm: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { setIsAuthenticated } = useAuth();
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await login(username, password);
      console.log('Login response:', response);
      localStorage.setItem('token', response.token);
      localStorage.setItem('clientId', response.clientId.toString());
      setIsAuthenticated(true);
      console.log('Navigating to /notes');
      navigate('/notes');
    } catch (error) {
      console.error('Login failed', error);
      setIsAuthenticated(false);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default LoginForm;