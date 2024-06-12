// src/components/AddNoteForm.tsx
import React, { useState } from 'react';
import { addNote, Note } from '../../services/api';

interface AddNoteFormProps {
  onNoteAdded: () => void;
}

const AddNoteForm: React.FC<AddNoteFormProps> = ({ onNoteAdded }) => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const newNote: Note = { id: 0, title, content, clientId: 1 };
      await addNote(newNote);
      setTitle('');
      setContent('');
      onNoteAdded();
    } catch (error) {
      console.error('Failed to add note', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        placeholder="Content"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <button type="submit">Add Note</button>
    </form>
  );
};

export default AddNoteForm;
