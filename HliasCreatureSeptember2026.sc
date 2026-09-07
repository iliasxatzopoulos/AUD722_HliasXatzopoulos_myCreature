HliasCreatureSeptember2026 : Creature {

    // ==================================================
    // Κοινός sound engine
    // Body = Dog Bark
    // Soul = SinOsc
    // Body * Soul = Ring Modulation
    // ==================================================

    makeSound { |rate = 1, modFreq = 440, amp = 0.7, dur = 5, mode = 0|

        this add: {

            var env;
            var body;
            var soul;
            var signal;
            var grains;
            var echo;

            // Amplitude envelope
            env = EnvGen.kr(
                Env.linen(0.1, dur - 0.2, 0.1),
                doneAction: 2
            );

            // BODY - dog bark
            body = PlayBuf.ar(
                1,
                this.buffer.bufnum,
                rate,
                loop: 1
            );

            // SOUL - oscillator
            soul = SinOsc.ar(modFreq);

            // RING MODULATION
            signal = body * soul;

            // ------------------------------------------
            // MODE 0 - natural / simple
            // ------------------------------------------

            if(mode == 0, {
                signal = signal;
            });


            // ------------------------------------------
            // MODE 1 - granular / digital
            // ------------------------------------------

            if(mode == 1, {

                grains = GrainBuf.ar(
                    2,
                    Impulse.kr(14),
                    0.08,
                    this.buffer.bufnum,
                    rate,
                    LFNoise1.kr(1).range(0, 1),
                    4,
                    LFNoise2.kr(0.5),
                    64
                );

                signal = (signal * 0.35) + (grains * 0.65);
            });


            // ------------------------------------------
            // MODE 2 - slow + echo
            // ------------------------------------------

            if(mode == 2, {

                echo = CombC.ar(
                    signal,
                    1.0,
                    0.32,
                    1.4
                );

                signal = signal + (echo * 0.4);
            });


            // ------------------------------------------
            // MODE 3 - robotic / electronic
            // ------------------------------------------

            if(mode == 3, {

                signal = BPF.ar(
                    signal,
                    700,
                    0.35
                );
            });


            // ------------------------------------------
            // MODE 4 - danger / distortion
            // ------------------------------------------

            if(mode == 4, {

                signal = (signal * 5).tanh;

                signal = signal + (body * 0.2);
            });


            // Envelope + amplitude
            signal = signal * env * amp;

            // Stereo output
            signal ! 2;

        }.play;
    }


    // ==================================================
    // DAWN
    // ==================================================

    dawn {
        "Hlias Creature: dawn".postln;

        this.makeSound(
            rate: 1.0,
            modFreq: 440,
            amp: 0.7,
            dur: 6,
            mode: 0
        );
    }


    // ==================================================
    // DAY
    // ==================================================

    day {
        "Hlias Creature: day".postln;

        this.makeSound(
            rate: 1.15,
            modFreq: 180,
            amp: 0.6,
            dur: 8,
            mode: 1
        );
    }


    // ==================================================
    // DUSK
    // ==================================================

    dusk {
        "Hlias Creature: dusk".postln;

        this.makeSound(
            rate: 0.65,
            modFreq: 80,
            amp: 0.6,
            dur: 7,
            mode: 2
        );
    }


    // ==================================================
    // NIGHT
    // ==================================================

    night {
        "Hlias Creature: night".postln;

        this.makeSound(
            rate: 0.5,
            modFreq: 320,
            amp: 0.35,
            dur: 6,
            mode: 3
        );
    }


    // ==================================================
    // DANGER
    // ==================================================

    danger {
        "Hlias Creature: danger".postln;

        this.makeSound(
            rate: 1.35,
            modFreq: 95,
            amp: 0.8,
            dur: 8,
            mode: 4
        );
    }

}
