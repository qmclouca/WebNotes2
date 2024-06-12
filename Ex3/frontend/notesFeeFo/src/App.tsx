// src/App.tsx
import React, { useState } from 'react';
import LoginForm from './components/LoginForm/LoginForm';
import AddNoteForm from './components/AddNoteForm/AddNoteForm';
import NoteList from './components/NoteList/NoteList';

const App: React.FC = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  return (
    <div>
      {isLoggedIn ? (
        <div>
          <AddNoteForm onNoteAdded={() => window.location.reload()} />
          <NoteList />
        </div>
      ) : (
        <LoginForm onLogin={handleLogin} />
      )}
    </div>
  );
};

export default App;
