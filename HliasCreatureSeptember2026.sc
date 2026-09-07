HliasCreatureSeptember2026 : Creature {

    // --------------------------------------------------
    // Κοινός sound engine
    // --------------------------------------------------

    makeSound { |rate = 1, modFreq = 0, amp = 0.7, dur = 5, mode = 0|

        this add: {
            var env;
            var sound;
            var grains;
            var echo;
            var mod;
            var signal;

            env = EnvGen.kr(
                Env.linen(0.1, dur - 0.2, 0.1),
                doneAction: 2
            );

            sound = PlayBuf.ar(
                1,
                this.buffer.bufnum,
                rate,
                loop: 0
            );

            // Normal bark
            if(mode == 0, {
                signal = sound;
            });

            // Granular / digital
            if(mode == 1, {
                grains = GrainBuf.ar(
                    2,
                    Impulse.kr(14),
                    0.08,
                    this.buffer.bufnum,
                    rate,
                    LFNoise1.kr(1).range(0, 1),
                    4,
                    LFNoise2.kr(0.5)
                );

                signal = (sound * 0.3) + (grains * 0.7);
            });

            // Slow + echo
            if(mode == 2, {
                echo = CombC.ar(
                    sound,
                    1.0,
                    0.32,
                    1.4
                );

                signal = sound + (echo * 0.4);
            });

            // Robotic / electronic
            if(mode == 3, {
                mod = SinOsc.ar(modFreq);
                
                signal = sound * mod;

                signal = BPF.ar(
                    signal,
                    700,
                    0.35
                );
            });

            // Danger / distorted
            if(mode == 4, {
                mod = SinOsc.ar(modFreq);

                signal = sound * mod;

                signal = (signal * 5).tanh;

                signal = signal + (
                    sound * 0.25
                );
            });

            signal * env * amp ! 2;

        }.play;
    }


    // --------------------------------------------------
    // DAWN
    // --------------------------------------------------

    dawn {
        "Hlias Creature: dawn".postln;

        this.makeSound(
            rate: 1.0,
            amp: 0.7,
            dur: 6,
            mode: 0
        );
    }


    // --------------------------------------------------
    // DAY
    // --------------------------------------------------

    day {
        "Hlias Creature: day".postln;

        this.makeSound(
            rate: 1.15,
            amp: 0.6,
            dur: 8,
            mode: 1
        );
    }


    // --------------------------------------------------
    // DUSK
    // --------------------------------------------------

    dusk {
        "Hlias Creature: dusk".postln;

        this.makeSound(
            rate: 0.65,
            amp: 0.6,
            dur: 7,
            mode: 2
        );
    }


    // --------------------------------------------------
    // NIGHT
    // --------------------------------------------------

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


    // --------------------------------------------------
    // DANGER
    // --------------------------------------------------

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
