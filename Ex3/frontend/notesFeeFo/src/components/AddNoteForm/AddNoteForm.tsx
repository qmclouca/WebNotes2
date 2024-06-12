// src/components/AddNoteForm.tsx
import React, { useState } from 'react';
import { addNote, Note } from '../../services/api';
import NoteList from '../NoteList/NoteList';

const Notes: React.FC = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const handleAddNote = async () => {
    const newNote: Note = { id: 0, title, content, clientId: 1 };
    await addNote(newNote);
    setTitle('');
    setContent('');
  };

  return (
    <div>
      <NoteList notes={[]} />
      <div>
        <h2>Add a new note</h2>
        <input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
        <textarea placeholder="Content" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
        <button onClick={handleAddNote}>Add Note</button>
      </div>
    </div>
  );
};

export default Notes;