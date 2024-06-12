// src/components/NoteList.tsx
import React, { useEffect, useState } from 'react';
import { getNotes, deleteNote, updateNote, Note } from '../../services/api';

const NoteList: React.FC = () => {
  const [notes, setNotes] = useState<Note[]>([]);
  const [editingNote, setEditingNote] = useState<Note | null>(null);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  useEffect(() => {
    fetchNotes();
  }, []);

  const fetchNotes = async () => {
    const fetchedNotes = await getNotes();
    setNotes(fetchedNotes);
  };

  const handleDelete = async (id: number) => {
    await deleteNote(id);
    fetchNotes();
  };

  const handleEdit = (note: Note) => {
    setEditingNote(note);
    setTitle(note.title);
    setContent(note.content);
  };

  const handleUpdate = async (e: React.FormEvent) => {
    e.preventDefault();
    if (editingNote) {
      await updateNote(editingNote.id, { ...editingNote, title, content });
      setEditingNote(null);
      setTitle('');
      setContent('');
      fetchNotes();
    }
  };

  return (
    <div>
      {editingNote ? (
        <form onSubmit={handleUpdate}>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
          <button type="submit">Update Note</button>
        </form>
      ) : (
        notes.map((note) => (
          <div key={note.id}>
            <h3>{note.title}</h3>
            <p>{note.content}</p>
            <button onClick={() => handleEdit(note)}>Edit</button>
            <button onClick={() => handleDelete(note.id)}>Delete</button>
          </div>
        ))
      )}
    </div>
  );
};

export default NoteList;
