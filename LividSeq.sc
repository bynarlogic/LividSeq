
MIDIClient.init;
m = MIDIOut(3);
'set transport
TempoClock.default.tempo = 120/60;



'Livid Block Seq

'Playback indicator for top row

(
a  = Pbind(\midinote,Pseq([0, 8, 16, 24, 32, 40, 48, 56],inf),\dur, 0.25);
s  = (a<>(type:\midi,midiout:m)).play(quant:4);
)

s.stop;
s.play;



MIDIIn.connectAll;

'make button state dictionary object
(
b = Dictionary.newFrom(List[0:0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,
8:0,9:0,10:0,11:0,12:0,13:0,14:0,15:0,
16:0,17:0,18:0,19:0,20:0,21:0,22:0,
23:0,24:0,25:0,26:0,27:0,28:0,29:0,30:0,31:0,32:0,33:0,
34:0,35:0,36:0,37:0,38:0,39:0,40:0,41:0,42:0,43:0,44:0,45:0,
46:0,47:0,48:0,49:0,50:0,51:0,52:0,53:0,54:0,55:0,56:0,57:0,58:0,59:0,60:0,61:0,62:0,63:0]);
)


'toggle buttons
MIDIdef.noteOn(\togglebutton, {arg x,y,z,j;
	~state = b.at(y);
	if(~state==1,{m.noteOn(16,y,0);b.add(y->0);},{m.noteOn(16,y,60);b.add(y->1);})});


'Row 2 on Livid Seq


Pdef(\seq, Pbind(\degree, Pseq([Ppatlace([Pfunc{b.at(1)},Pfunc{b.at(9)},Pfunc{b.at(17)},Pfunc{b.at(25)},Pfunc{b.at(33)},Pfunc{b.at(41)},Pfunc{b.at(49)},Pfunc{b.at(57)}],inf)],inf),\dur, 0.5, \preamp, 4.5));
Pbindf(Pdef(\seq)).play(quant:4);



Pdef(\seq, Pbind(\instrument, "kik",\amp, Pseq([Ppatlace([Pfunc{b.at(1)},Pfunc{b.at(9)},Pfunc{b.at(17)},Pfunc{b.at(25)},Pfunc{b.at(33)},Pfunc{b.at(41)},Pfunc{b.at(49)},Pfunc{b.at(57)}],inf)],inf),\dur, 0.25));
Pbindf(Pdef(\seq)).play(quant:4);


SynthDef("Josh", {
	arg freq = 440, amp = 1;
	var out, env, snd;
	snd = LPF.ar(Saw.ar(freq),MouseX.kr(200, 2500));
	env = EnvGen.ar(Env.perc,doneAction:2);
	out = (snd * amp) * env;
	Out.ar(0, out ! 2);
	}
).add





