import xml.etree.ElementTree as ET
import sqlite3 as DB
import codecs

path = "."

class JKanji:
	def __init__(self, value, infs, pris):
		self.value = value
		self.pris = pris
		self.infs = infs

	def value_to_str(self):
		return self.value if self.value is not None else ""

	def inf_to_str(self):
		return "|".join(filter(None, self.infs))

	def pri_to_str(self):
		return "|".join(filter(None, self.pris))

class JReading:
	def __init__(self, value, no_kanji, infs, pris, restr):
		self.value = value
		self.no_kanji = no_kanji
		self.infs = infs
		self.pris = pris
		self.restr = restr

	def value_to_str(self):
		return self.value if self.value is not None else ""

	def inf_to_str(self):
		return "|".join(filter(None, self.infs))

	def pri_to_str(self):
		return "|".join(filter(None, self.pris))

	def restr_to_str(self):
		return "|".join(filter(None, self.restr))

	def no_kanji_to_str(self):
		return "T" if self.no_kanji else "F"

class Gloss:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

class Example:
	def __init__(self, text, sentences):
		self.text = text
		self.sentences = sentences

	def sentences_to_str(self):
		return "/".join(filter(None, self.sentences))

class Sense:
	def __init__(self, ants, pos, fields, dials, glosses, exs):
		self.ants = ants
		self.pos = pos
		self.fields = fields
		self.dials = dials
		self.glosses = glosses
		self.exs = exs

	def ant_to_str(self):
		return "|".join(filter(None, self.ants))

	def pos_to_str(self):
		return "|".join(filter(None, self.pos))

	def fields_to_str(self):
		return "|".join(filter(None, self.fields))

	def gloss_to_str(self):
		return "|".join(filter(None, map(lambda x: x.to_str(), self.glosses)))

	def dials_to_str(self):
		return "|".join(filter(None, self.dials))

	def ex_text_to_str(self):
		return "|".join(filter(None, map(lambda x: x.text, self.exs)))

	def ex_sent_to_str(self):
		return "|".join(filter(None, map(lambda x: x.sentences_to_str(), self.exs)))

class DicReference:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

class QCode:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

class Reading:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

class Radical:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)


class Variant:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

class Meaning:
	def __init__(self, value, _type):
		self.value = value
		self._type = _type

	def to_str(self):
		return "{}-{}".format(self.value, self._type)

def create_tables(cur):
	cur.execute(
		'''
		CREATE TABLE IF NOT EXISTS entry(
		    id INTEGER NOT NULL PRIMARY KEY,
		    keb TEXT,
		    ke_inf TEXT,
		    ke_pri TEXT,
		    re TEXT,
		    re_nokanji TEXT,
		    re_restr TEXT,
		    re_inf TEXT,
		    re_pri TEXT,
		    ant TEXT,
		    pos TEXT,
		    field TEXT,
		    dial TEXT,
		    gloss TEXT,
		    ex_text TEXT,
		    ex_sent TEXT
		)
		'''
	)

	cur.execute(
		'''
		CREATE TABLE IF NOT EXISTS kanji(
		    literal TEXT NOT NULL,
		    radical TEXT,
		    grade TEXT,
		    stroke_count INTEGER,
		    variant TEXT,
		    freq INTEGER,
		    rad_name TEXT,
		    jlpt INTEGER,
		    dic_number TEXT,
		    reading TEXT,
		    meaning TEXT,
		    nanori TEXT,
		    q_code TEXT
		)
		'''
	)

	cur.execute(
		'''
		CREATE TABLE IF NOT EXISTS sentence(
			japanese TEXT,
			english TEXT
		)
		'''
	)

	cur.execute(
		'''
		CREATE TABLE IF NOT EXISTS jlpt(
			word TEXT,
			level INTEGER
		)
		'''
	)

def parse_jmdict(cur):
	tree = ET.parse('{}/jmdict.xml'.format(path))

	root = tree.getroot()

	entries = []
	i = 0

	for entry in root.iter('entry'):

		i += 1

		if i == 10000:
			i = 0
			cur.execute('BEGIN TRANSACTION')
			for (_id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent) in entries:
				statement = '''
					INSERT INTO entry(id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent) 
					VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				'''
				cur.execute(statement, [_id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent])
			entries = []
			cur.execute('COMMIT')

		_id = entry.find('ent_seq').text
		kanjis = []
		readings = []
		senses = []

		for kanji in entry.iter('k_ele'):
			item = JKanji(kanji.find('keb').text, 
				map(lambda x: x.text, kanji.findall('ke_inf')), 
				map(lambda x: x.text, kanji.findall('ke_pri'))
			)
			kanjis.append(item)
			print('Kanji {}'.format(item.value))

		for reading in entry.iter('r_ele'):
			item = JReading(
				reading.find('reb').text, 
				reading.find('re_nokanji') is not None, 
				map(lambda x: x.text, reading.findall('re_inf')), 
				map(lambda x: x.text, reading.findall('re_pri')), 
				map(lambda x: x.text, reading.findall('re_restr'))
			)
			readings.append(item)
			print('Reading {}'.format(item.value))

		for sense in entry.iter('sense'):
			glosses = []
			examples = []

			for gloss in sense.findall('gloss'):
				glosses.append(Gloss(gloss.text, gloss.attrib.get('g_type')))

			for example in sense.findall('example'):
				text = example.find('ex_text').text
				sentences = map(lambda x: x.text, example.findall('ex_sent'))

				examples.append(Example(text, sentences))

			item = Sense(
				map(lambda x: x.text, sense.findall('ant')),
				map(lambda x: x.text, sense.findall('pos')),
				map(lambda x: x.text, sense.findall('fields')),
				map(lambda x: x.text, sense.findall('dials')),
				glosses,
				examples
			)

			senses.append(item)
			print(item)

		entries.append(( 
			_id, 
			";".join(map(lambda x: x.value_to_str(), kanjis)), 
			";".join(map(lambda x: x.inf_to_str(), kanjis)), 
			";".join(map(lambda x: x.pri_to_str(), kanjis)), 
			";".join(map(lambda x: x.value_to_str(), readings)), 
			";".join(map(lambda x: x.no_kanji_to_str(), readings)), 
			";".join(map(lambda x: x.restr_to_str(), readings)), 
			";".join(map(lambda x: x.inf_to_str(), readings)), 
			";".join(map(lambda x: x.pri_to_str(), readings)), 
			";".join(map(lambda x: x.ant_to_str(), senses)), 
			";".join(map(lambda x: x.pos_to_str(), senses)), 
			";".join(map(lambda x: x.fields_to_str(), senses)), 
			";".join(map(lambda x: x.dials_to_str(), senses)), 
			";".join(map(lambda x: x.gloss_to_str(), senses)),
			";".join(map(lambda x: x.ex_text_to_str(), senses)), 
			";".join(map(lambda x: x.ex_sent_to_str(), senses))
		))

	cur.execute('BEGIN TRANSACTION')
	for (_id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent) in entries:
		statement = '''
			INSERT INTO entry(id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent) 
			VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
		'''
		cur.execute(statement, [_id, keb, ke_inf, ke_pri, re, re_nokanji, re_restr, re_inf, re_pri, ant, pos, field, dial, gloss, ex_text, ex_sent])
	cur.execute('COMMIT')

def parse_kanjidic(cur):
	tree = ET.parse('{}/kanjidic.xml'.format(path))

	root = tree.getroot()

	entries = []
	i = 0

	for entry in root.iter('character'):
		i += 1

		if i == 10000:
			cur.execute('BEGIN TRANSACTION')
			i = 0
			for (literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code) in entries:
				statement = '''
					INSERT INTO kanji(literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code) 
					VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				'''
				cur.execute(statement, [literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code])
			entries = []
			cur.execute('COMMIT')

		literal = entry.find('literal').text
    
		jlpt = None
		freq = None
		grade = None
		stroke_count = None
		variants = []
		rad_names = []

		if entry.find('misc') is not None:
			if entry.find('misc').find('jlpt') is not None:
				jlpt = int(entry.find('misc').find('jlpt').text)

			if entry.find('misc').find('freq') is not None:
				freq = int(entry.find('misc').find('freq').text)
			
			if entry.find('misc').find('grade') is not None:
				grade = entry.find('misc').find('grade').text

			if entry.find('misc').find('stroke_count') is not None:
				stroke_count = int(entry.find('misc').find('stroke_count').text)

			if entry.find('misc').find('variant') is not None:
				for variant in entry.find('misc').find('variant').findall('radvalue'):
					variants.add(Variant(variant.text, variant.attrib.get('rad_type')))

			for rad_name in entry.find('misc').findall('rad_name'):
				rad_names.append(rad_name.text)

		dic_refs = []
		q_codes = []
		readings = []
		meanings = []
		nanoris = []
		radicals = []

		if entry.find('dic_number') is not None:
			for dic in entry.find('dic_number').findall('dic_ref'):
				dic_refs.append(DicReference(dic.text, dic.attrib.get('dr_type')))

		if entry.find('query_code') is not None:
			for qcode in entry.find('query_code').findall('q_code'):
				q_codes.append(QCode(qcode.text, qcode.attrib.get('qc_type')))

		if entry.find('reading_meaning') is not None:

			if entry.find('reading_meaning').find('rmgroup') is not None:
				for re in entry.find('reading_meaning').find('rmgroup').findall('reading'):
					readings.append(Reading(re.text, re.attrib.get('r_type')))

				for meaning in entry.find('reading_meaning').find('rmgroup').findall('meaning'):
					meanings.append(Meaning(meaning.text, meaning.attrib.get('m_lang')))

			for nanori in entry.find('reading_meaning').findall('nanori'):
				nanoris.append(nanori.text)

		if entry.find('radical') is not None:
			for radical in entry.find('radical').findall('rad_value'):
				radicals.append(Radical(radical.text, radical.attrib.get('rad_type')))

		print('Kanji {}'.format(literal))

		entries.append((
			literal, 
			";".join(map(lambda x: x.to_str(), radicals)), 
			grade, 
			stroke_count, 
			";".join(map(lambda x: x.to_str(), variants)), 
			freq, 
			";".join(rad_names), 
			jlpt, 
			";".join(map(lambda x: x.to_str(), dic_refs)), 
			";".join(map(lambda x: x.to_str(), readings)), 
			";".join(map(lambda x: x.to_str(), meanings)), 
			";".join(nanoris), 
			";".join(map(lambda x: x.to_str(), q_codes))
		))

	cur.execute('BEGIN TRANSACTION')
	for (literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code) in entries:
		statement = '''
			INSERT INTO kanji(literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code) 
			VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
		'''
		cur.execute(statement, [literal, radical, grade, stroke_count, variant, freq, rad_name, jlpt, dic_number, reading, meaning, nanori, q_code])
	cur.execute('COMMIT')

def parse_sentences(cur):
	file = open('{}/sentences.tsv'.format(path))
	lines = file.readlines()

	entries = []
	i = 0

	for line in lines:
		i += 1

		if i == 10000:
			print('commit')
			cur.execute('BEGIN TRANSACTION')
			for (en, jp) in entries:
				statement = '''
					INSERT INTO sentence(japanese, english)
					VALUES(?, ?)
				'''
				cur.execute(statement, [jp, en])
			entries = []
			cur.execute('COMMIT')
			i = 0

		(en, jp) = line.split('|')
		entries.append((unicode(en, 'utf-8'), unicode(jp, 'utf-8')))

	cur.execute('BEGIN TRANSACTION')
	for (en, jp) in entries:
		statement = '''
			INSERT INTO sentence(japanese, english)
			VALUES(?, ?)
		'''
		cur.execute(statement, [jp, en])
	cur.execute('COMMIT')

def parse_jlpt(cur, jlpt):
	file = open('{}/n{}'.format(path, jlpt))
	lines = file.readlines()

	entries = []
	i = 0

	for line in lines:
		i += 1

		if i == 10000:
			print('commit')
			cur.execute('BEGIN TRANSACTION')
			for entry in entries:
				statement = '''
					INSERT INTO jlpt(word, level)
					VALUES(?, ?)
				'''
				cur.execute(statement, [entry, jlpt])
			entries = []
			cur.execute('COMMIT')
			i = 0

		entries.append(line)

	cur.execute('BEGIN TRANSACTION')
	for entry in entries:
		statement = '''
			INSERT INTO jlpt(word, level)
			VALUES(?, ?)
		'''
		cur.execute(statement, [entry, jlpt])
	cur.execute('COMMIT')


import csv

def parse_conjo(cur):
	cur.execute(
		'''
		CREATE TABLE IF NOT EXISTS conjugation(
			pos INTEGER,
			conj INTEGER,
	        negative TEXT,
	        formal TEXT,
	        onum INTEGER,
	        stem INTEGER,
	        okurigana TEXT,
	        euphr TEXT,
	        euphk TEXT
		);
		'''
	)

	cur.execute('BEGIN TRANSACTION')
	with open('{}/conjo.csv'.format(path), newline='') as csvfile:
	    reader = csv.reader(csvfile, delimiter=' ')
	    next(reader)
	    for row in reader:
	        print(', '.join(row[0].split('\t')[:-1]))
	        statement = '''
	            INSERT INTO conjugation(pos, conj, negative, formal, onum, stem, okurigana, euphr, euphk)
	            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
	        '''
	        cur.execute(statement, row[0].split('\t')[:-1])
	    cur.execute('COMMIT')

conn = DB.connect('{}/jmdict.db'.format(path))
cur = conn.cursor()

# print('create tables')
# create_tables(cur)

# print('parse jmdict')
# parse_jmdict(cur)

# print('parse kanjidic')
# parse_kanjidic(cur)

# print('parse sentences')
# parse_sentences(cur)

# print('parse jlpt')
# parse_jlpt(cur, 2)
# parse_jlpt(cur, 3)
# parse_jlpt(cur, 4)
# parse_jlpt(cur, 5)

print('parse conjugation')
parse_conjo(cur)

conn.close()